import { ViewController, IonicPage, NavParams, ModalController } from 'ionic-angular';
import { Component } from '@angular/core';


@IonicPage()
@Component({
  selector: 'page-main-search-popover',
  templateUrl: 'main-search-popover.html',
})
export  class MainSearchPopoverPage {
    private mainPage;
    constructor(public navParams: NavParams, public viewCtrl: ViewController, private modalCtrl: ModalController) {
      this.mainPage = navParams.get("mainPage");
    }
    
    toggleEdit() {
      this.mainPage.toggleEdit();
      this.close();
    }

    openMap() {
      this.mainPage.openMap();
      this.close();
    }

    close() {
      this.viewCtrl.dismiss();
    }
  }