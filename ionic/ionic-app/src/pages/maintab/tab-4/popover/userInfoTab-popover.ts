import { ViewController, IonicPage, NavParams, ModalController } from 'ionic-angular';
import { Component } from '@angular/core';


@IonicPage()
@Component({
  selector: 'page-userinfotab-popover',
  templateUrl: 'userInfoTab-popover.html',
})
export  class UserInfoTabPopoverPage {
    private mainPage;
    constructor(public navParams: NavParams, public viewCtrl: ViewController, private modalCtrl: ModalController) {
      this.mainPage = navParams.get("mainPage");
    }
    

    editUserData() {
      this.mainPage.editUserData();
      this.close();
    }

    logout(){
      this.mainPage.logout();
      this.close();
    }

    close() {
      this.viewCtrl.dismiss();
    }
  }