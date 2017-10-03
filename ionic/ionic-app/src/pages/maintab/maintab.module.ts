import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MaintabPage } from './maintab';

@NgModule({
  declarations: [
    MaintabPage,
  ],
  imports: [
    IonicPageModule.forChild(MaintabPage),
  ]
})
export class MaintabPageModule {}
