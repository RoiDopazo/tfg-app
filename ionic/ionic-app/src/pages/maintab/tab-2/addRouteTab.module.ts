import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AddRouteTabPage } from './addRouteTab';
import { CalendarModule } from "ion2-calendar";

@NgModule({
  declarations: [
    AddRouteTabPage,
  ],
  imports: [
    IonicPageModule.forChild(AddRouteTabPage),
    CalendarModule
  ],
})
export class AddRouteTabPageModule {}
