
import akka.actor.ActorSystem
import akka.testkit._
import org.scalatest.{MustMatchers, WordSpec, WordSpecLike}
import simplefsm.characters._
import akka.actor.Props
import org.scalatest.BeforeAndAfterAll



class JulesTest  extends WordSpec
  with MustMatchers {

  "A simple actor" must {

    implicit val system = ActorSystem()
    val fsm = TestFSMRef(new Jules())


    "receive messages" in {

      assert(fsm.stateName == Calm)
      assert(fsm.stateData == JulesStateData())
      fsm ! What // being a TestActorRef, this runs also on the CallingThreadDispatcher

      assert(fsm.stateName == Angry)
      fsm ! What
      assert(fsm.stateName == Angry)
    }
  }
}

class JulesTest2 extends TestKit(ActorSystem("MySpec")) with ImplicitSender
 with MustMatchers with WordSpecLike
  with BeforeAndAfterAll {



  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A simple fsm" must {

    val jules = system.actorOf(Props(new Jules))
    
    "receive messages" in {

        jules ! What
        expectMsg(SayWhatMotherFucker)

    }
  }
}