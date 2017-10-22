import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';

import { ModalListdaysPage } from './modal-listdays';

@NgModule({
  declarations: [
    ModalListdaysPage,
  ],
  imports: [
    IonicPageModule.forChild(ModalListdaysPage),
  ],
  entryComponents: [
    ModalListdaysPage,
  ]
})
export class ModalListdaysPageModule {}