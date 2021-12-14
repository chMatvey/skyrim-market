import firebase from 'firebase'
import FirebaseError = firebase.FirebaseError

export function toMessage(error: FirebaseError): string {
  return error.message
}
