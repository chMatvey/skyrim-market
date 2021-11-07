import { Injectable } from '@angular/core'
import { State } from '@ngxs/store'

export interface MasterStateModel {
}

const defaults: MasterStateModel = {
}

@State<MasterStateModel>({
  defaults,
  name: 'master'
})
@Injectable()
export class MasterState {
}
