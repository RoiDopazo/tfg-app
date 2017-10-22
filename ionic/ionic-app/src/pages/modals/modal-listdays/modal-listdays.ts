import { Component } from '@angular/core';
import { IonicPage, NavParams, ViewController } from 'ionic-angular';
import { Renderer } from '@angular/core';

@IonicPage()
@Component({
    selector: 'page-modal-listdays',
    templateUrl: 'modal-listdays.html',
  })
export class ModalListdaysPage {
  myParam: string;

  constructor(public viewCtrl: ViewController, params: NavParams, private renderer: Renderer) {
    this.myParam = params.get('param1');
    this.renderer.setElementClass(viewCtrl.pageRef().nativeElement, 'my-popup', true);
  }

  dismiss() {
    this.viewCtrl.dismiss();
  }
}