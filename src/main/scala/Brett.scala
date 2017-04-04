
package simplefsm.characters

import akka.actor._

// messages for Brett
sealed trait BrettMessage
object What extends BrettMessage
object BrettDies extends BrettMessage

// states for Brett
sealed trait BrettState
case object Agitated extends BrettState
case object Scared extends BrettState
case object ShitScared extends BrettState
case object Dead extends BrettState


final case class BrettData()

class Brett extends Actor with FSM[BrettState, BrettData] {

  startWith(Agitated, BrettData())

  when(Agitated){
    case Event(SayWhat, _) =>
      goto(Scared) using BrettData() replying What
  }

  when(Scared){
    case Event(SayWhatMotherFucker, _) =>
      goto(ShitScared) using BrettData() replying What
  }

  when(ShitScared) {
    case Event(ShootBrett, _) =>
      goto(Dead) using BrettData() replying BrettDies
  }
}
