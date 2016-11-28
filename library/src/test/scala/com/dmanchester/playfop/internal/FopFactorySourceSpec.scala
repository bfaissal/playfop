package com.dmanchester.playfop.internal

import org.specs2.mutable.Specification

class FopFactoryCacheSpec extends Specification {

  private val BogusFopVersion = "123.456"
  
  private val FopConfig =
    <fop version={BogusFopVersion}/>
  
  "getWithDefaultConfig()" should {
    "return the same instance when called repeatedly" in {
      val cache = new FopFactorySource()
      cache.getWithDefaultConfig must beTheSameAs(cache.getWithDefaultConfig)
    }
  }

  "getWithCustomConfig(fopConfigXml)" should {
    "return the same instance when called repeatedly with the same fopConfigXml" in {
      val cache = new FopFactorySource()
      cache.getWithCustomConfig(FopConfig) must beTheSameAs(
        cache.getWithCustomConfig(FopConfig)
      )
    }

    "apply the fopConfigXml it receives" in {
      val cache = new FopFactorySource()
      val factory = cache.getWithCustomConfig(FopConfig)
      factory.getUserConfig.getAttribute("version") must beEqualTo(BogusFopVersion)
    }
  }
}