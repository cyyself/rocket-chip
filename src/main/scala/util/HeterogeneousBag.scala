// See LICENSE.SiFive for license details.

package freechips.rocketchip.util

import chisel3._
import chisel3.experimental.DataMirror
import scala.collection.immutable.VectorMap

final case class HeterogeneousBag[T <: Data](elts: Seq[T]) extends Record with collection.IndexedSeq[T] {
  val elements = VectorMap(elts.zipWithIndex.map { case (n,i) => (i.toString, DataMirror.internal.chiselTypeClone(n)) }:_*)

  def apply(x: Int): T = {
    val key = elements.keys(x) // VectorMap.keys returns a Vector
    elements(key)
  }
  def length = elts.length

  override def className: String = super.className
  // IndexedSeq has its own hashCode/equals that we must not use
  override def hashCode: Int = super[Record].hashCode
  override def equals(that: Any): Boolean = super[Record].equals(that)
}

object HeterogeneousBag
{
  def fromNode[D <: Data, E](elts: Seq[(D, E)]) = new HeterogeneousBag(elts.map(_._1.cloneType))
}
