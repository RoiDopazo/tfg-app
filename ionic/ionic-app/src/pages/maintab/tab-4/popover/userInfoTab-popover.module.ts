import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { UserInfoTabPopoverPage } from './userInfoTab-popover'

@NgModule({
  declarations: [
    UserInfoTabPopoverPage,
  ],
  imports: [
    IonicPageModule.forChild(UserInfoTabPopoverPage),
  ],
})
export class UserInfoTabPopoverPageModule {}
