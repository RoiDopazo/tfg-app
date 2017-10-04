import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainSearchPage } from './main-search';

@NgModule({
  declarations: [
    MainSearchPage,
  ],
  imports: [
    IonicPageModule.forChild(MainSearchPage),
  ],
})
export class MainSearchPageModule {}
