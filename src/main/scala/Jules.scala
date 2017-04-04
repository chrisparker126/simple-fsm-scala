package simplefsm.characters

import akka.actor._
import com.typesafe.scalalogging._


// some message for jules
sealed trait JulesMessage
object SayWhat extends JulesMessage
object SayWhatMotherFucker extends JulesMessage
object ShootBrett extends JulesMessage

/**
  *
  */
sealed trait JulesState
case object Calm extends JulesState
case object Angry extends JulesState
case object Smiling extends JulesState

/**
  *
  */
final case class JulesStateData()

class Jules extends Actor with FSM[JulesState, JulesStateData] with LazyLogging {



  startWith(Calm, JulesStateData())

  when(Calm) {
    case Event(What, _) => {
      logger.warn("I'm angry")
      goto(Angry) using JulesStateData() replying SayWhatMotherFucker
    }
  }

  when(Angry) {
    case Event(What, _) =>
      stay using JulesStateData() replying ShootBrett
    case Event(BrettDies, _) =>
      goto(Smiling) using JulesStateData()
  }
}