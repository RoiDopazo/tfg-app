import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

/**
 * Generated class for the RegisterPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {

  private registerCredentials= { username: '', password: '', email: '' };
  private form: FormGroup;

  constructor(public navCtrl: NavController, public navParams: NavParams, private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeat_password: ['', Validators.required]
    });
  }

  logForm(){
    console.log(this.form.value)
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad RegisterPage');
  }

  showLogin() {
    this.navCtrl.pop();
  }

  register() {

  }
}
