package tasks.typeclasses

import u03.Sequences

trait Foldable[F[_]]:
  extension [A](foldable: F[A])
    def foldRight[B](initial: B)(op: (A, B) => B): B
    def foldLeft[B](initial: B)(op: (B, A) => B): B
    def exists(predicate: A => Boolean): Boolean = foldLeft(false)((acc, el) => acc || predicate(el))

  
object Foldable:
  given Foldable[Sequences.Sequence] with 
    extension [A](foldable: Sequences.Sequence[A])
      def foldRight[B](initial: B)(op: (A, B) => B): B = foldable match
        case Sequences.Sequence.Cons(head, tail) => op(head, tail.foldRight(initial)(op))
        case Sequences.Sequence.Nil() => initial
      def foldLeft[B](initial: B)(op: (B, A) => B): B = foldable match
        case Sequences.Sequence.Cons(head, tail) => tail.foldLeft(op(initial, head))(op)
        case Sequences.Sequence.Nil() => initial