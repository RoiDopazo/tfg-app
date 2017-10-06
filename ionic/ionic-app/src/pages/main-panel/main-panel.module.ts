import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPanelPage } from './main-panel';

@NgModule({
  declarations: [
    MainPanelPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPanelPage),
  ],
})
export class MainPanelPageModule {}
