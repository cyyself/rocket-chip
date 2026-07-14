// Rocket + Saturn RVV 1.0 vector-unit integration configs.
//
// Saturn's `WithRocketVectorUnit` attaches its vector unit through rocket-chip's
// RocketCoreVectorParams / RocketVectorUnit interface and already advertises the
// "zvbb" vector-bitmanip extension.  VLEN is the configurable knob here; the
// datapath width (dLen) is kept at 64 so the L1 D$ row width matches the default
// cache/TileLink beat width.
//
// This source lives in the `saturn` mill module (which depends on rocketchip),
// so it can reference both Saturn and rocket-chip configs.

package freechips.rocketchip.system

import org.chipsalliance.cde.config.Config
import saturn.rocket.WithRocketVectorUnit

/** Attach the Saturn vector unit to every Rocket tile with the given VLEN. */
class WithSaturnVector(vLen: Int = 256, dLen: Int = 64)
  extends WithRocketVectorUnit(vLen = vLen, dLen = dLen)

class RocketSaturnConfig     extends Config(new WithSaturnVector(256, 64) ++ new DefaultConfig)
class RocketSaturnV128Config extends Config(new WithSaturnVector(128, 64) ++ new DefaultConfig)
class RocketSaturnV512Config extends Config(new WithSaturnVector(512, 64) ++ new DefaultConfig)
