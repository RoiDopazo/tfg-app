import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainSearchPopoverPage } from './main-search-popover';

@NgModule({
  declarations: [
    MainSearchPopoverPage,
  ],
  imports: [
    IonicPageModule.forChild(MainSearchPopoverPage),
  ],
})
export class MainSearchPopoverPageModule {}
