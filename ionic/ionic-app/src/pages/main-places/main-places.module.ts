import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPlacesPage } from './main-places';

@NgModule({
  declarations: [
    MainPlacesPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPlacesPage),
  ],
})
export class MainPlacesPageModule {}
