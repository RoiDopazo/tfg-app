import { ViewController, IonicPage, NavParams, ModalController } from 'ionic-angular';
import { Component } from '@angular/core';
import moment from "moment";

@IonicPage()
@Component({
  selector: 'page-main-search-popover',
  templateUrl: 'main-search-popover.html',
})


export  class MainSearchPopoverPage {

  private date2;
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

    setStartTime(select_day, selectedStartTime) {
      this.mainPage.updateStartTime(select_day, selectedStartTime);
      this.close();
    }
  }