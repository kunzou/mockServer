import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from './welcome/welcome.component';
import {RateCardComponent} from './rateCard/rateCard.component';

const routes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'rateCard', component: RateCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRouters {}