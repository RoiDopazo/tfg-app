import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPanelAboutPage } from './main-panel-about';

@NgModule({
  declarations: [
    MainPanelAboutPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPanelAboutPage),
  ],
})
export class MainPanelAboutPageModule {}
