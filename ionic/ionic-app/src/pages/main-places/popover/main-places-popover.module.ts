import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPlacesPopoverPage } from './main-places-popover';

@NgModule({
  declarations: [
    MainPlacesPopoverPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPlacesPopoverPage),
  ],
})
export class MainPlacesPopoverPageModule {}
