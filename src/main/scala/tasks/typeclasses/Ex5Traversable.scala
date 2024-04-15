package u04lab
import u03.Sequences.* 
import Sequence.*
import u04lab.Ex5Traversable.printlnAll

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def logOne[A](a: A): Unit = println("The next element is: "+a)

  def logAllSequence[A](seq: Sequence[A]): Unit = seq match
    case Cons(h, t) => logOne(h); logAllSequence(t)
    case _ => ()

  trait Traversable[T[_]]:
    def consumeAll[A](traversable: T[A])(consumer: A => Unit): Unit
  
  given Traversable[Sequence] with
    def consumeAll[A](traversable: Sequence[A])(consumer: A => Unit): Unit = traversable match
      case Sequence.Cons(h, t) => consumer(h); consumeAll(t)(consumer) 
      case Sequence.Nil() => ()
      
  def printlnAll[T[_], A](traversable: T[A])(using t: Traversable[T]): Unit =
    t.consumeAll(traversable)(println(_))

@main def tryTraversable = 
  printlnAll(Sequence.Cons("Pippo", Sequence.Cons("Pluto", Sequence.Nil())))

