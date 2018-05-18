import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AuthServiceProvider } from '../../providers/auth-service/auth-service';
import { ServiceManagerProvider } from '../../providers/services/service-manager';

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

  private form: FormGroup;

  constructor(public navCtrl: NavController, public navParams: NavParams, private formBuilder: FormBuilder, private authService: AuthServiceProvider, private serviceManager: ServiceManagerProvider) {
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.maxLength(16), Validators.required])],
      email: ['', Validators.compose([Validators.maxLength(16), Validators.required])],
      password: ['', Validators.compose([Validators.maxLength(16), Validators.required])],
      repeat_password: ['', Validators.compose([Validators.maxLength(16), Validators.required])]
    });
  }

  logForm(){
    if (this.form.value.password != this.form.value.repeat_password) {
      this.serviceManager.showError("Intentelo de nuevo", "Las passwords no coinciden");
    } else {
      let credentials = {
        "username": this.form.value.username,
        "password": this.form.value.password,
        "email": this.form.value.email
      };
      this.authService.register(credentials).then(
        access => {
          if (access == true) {
            this.authService.login(credentials).then(
              access => {
                if (access) {
                  this.navCtrl.setRoot("MaintabPage");
                } else {
                  this.serviceManager.showError("Acceso Denegado", "");
                }
              },
                error => {
                  this.serviceManager.showError("Error al loguearse en la aplicación", "");
                }
            )
          } else {
            this.serviceManager.showError("No se pudo completar el registro", access);
          }
        }
      );
    }
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
