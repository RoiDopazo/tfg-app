import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { UserInfoTabPage } from './userInfoTab';

@NgModule({
  declarations: [
    UserInfoTabPage,
  ],
  imports: [
    IonicPageModule.forChild(UserInfoTabPage),
  ],
})
export class UserInfoTabPageModule {}
