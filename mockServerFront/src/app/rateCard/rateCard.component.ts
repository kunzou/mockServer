import { RateCard } from '../domain/rateCard';
import {Component, ElementRef, ViewChild} from '@angular/core';
import {DataService} from '../data/data.service';
import {DataSource} from '@angular/cdk/table';
import {Observable} from 'rxjs/Observable';
import {PostDialogComponent} from '../post-dialog/post-dialog.component';
import {MatDialog} from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { catchError, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { of } from 'rxjs';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-rateCard',
  templateUrl: './rateCard.component.html',
  styleUrls: ['./rateCard.component.css']
})
export class RateCardComponent {
  @ViewChild("fileUpload", { static: false }) fileUpload: ElementRef; files = [];
  constructor(public dialog: MatDialog, private dataService: DataService) {
  }
  contentFC = new FormControl();
  displayedColumns = ['name', 'buyRate', 'maxFee', 'overageSplit', 'nbAdjustment', 'regAdjustment', 'delete', 'edit'];
  dataSource = new PostDataSource(this.dataService);
  rawData : string;

  deleteRateCard(id) {
    this.dataService.deleteRateCard(id).subscribe(result =>
      this.dataSource = new PostDataSource(this.dataService)
    );
  }

  deleteAllRateCards() {
    this.dataService.deleteAllRateCards().subscribe(result =>
      this.dataSource = new PostDataSource(this.dataService)
    );
  }

  editRateCard(data) {
    let dialogRef = this.dialog.open(PostDialogComponent, {
      width: '600px',
      data: data
    });
    dialogRef.componentInstance.event.subscribe((result) => {
      this.dataService.addRateCard(result.data).subscribe(
        result => this.dataSource = new PostDataSource(this.dataService)
      );
    });
  }

  openDialog(): void {
    let dialogRef = this.dialog.open(PostDialogComponent, {
      width: '600px',
      data: new RateCard()
    });
    dialogRef.componentInstance.event.subscribe((result) => {
      this.dataService.addRateCard(result.data).subscribe(
        result => this.dataSource = new PostDataSource(this.dataService)
      );
    });
  }

  exportToText(): void {
    this.dataService.getRateCardsAsCSV().subscribe(result => this.rawData = result)
  }

  importFromText(): void {
    this.dataService.importFromText(this.rawData).subscribe(result => this.dataSource = new PostDataSource(this.dataService))
  }

  //Textarea autosize
  descFC = new FormControl('', []);

  importFromFile(): void {
    const fileUpload = this.fileUpload.nativeElement; fileUpload.onchange = () => {
        Array.from(fileUpload.files).forEach(element => {
        this.files.push({ data: element, inProgress: false, progress: 0 });
      });

      this.fileUpload.nativeElement.value = '';
      this.files.forEach(file => {
        this.uploadFile(file);
      });
    };
    fileUpload.click();
  }

  saveFile(): void {
    this.dataService.getRateCardsAsCSV().subscribe(result =>
      saveAs(new Blob([result], {type: "text/plain;charset=utf-8"}), "ratecard.csv")
    );
}

  uploadFile(file): void {
    const formData = new FormData();
    formData.append('file', file.data);
    file.inProgress = true;
    this.dataService.upload(formData).pipe(
      map(event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            file.progress = Math.round(event.loaded * 100 / event.total);
            break;
          case HttpEventType.Response:
            return event;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        file.inProgress = false;
        return of(`${file.data.address} upload failed.`);
      })).subscribe((event: any) => {
        this.dataSource = new PostDataSource(this.dataService)
      });
  }
}

export class PostDataSource extends DataSource<any> {
  constructor(private dataService: DataService) {
    super();
  }

  connect(): Observable<RateCard[]> {
    return this.dataService.getRateCards();
  }

  disconnect() {
  }
}
