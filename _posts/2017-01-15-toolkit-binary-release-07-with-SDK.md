---
layout: post
title: "v0.7 binary released with Collector SDK and ApexBeats"
date: 2017-01-15
---

We are happy to announce the release of version 0.7. It includes support for ELK beats and is distributed with the Collector SDK.

What's new:

* `SDK` : both SDK and API have been open sourced with the project. You now can build your own handlers for the APEX collector and deploy them as an extension. Sample project included. Checkout the updated [docs](http://toolkits.verticle.io/docs/index.html#sdk-extend) for details
* `Beats` : the collector now support Elasticsearch's beat forwarding, visit [https://github.com/verticle-io/apexbeats](https://github.com/verticle-io/apexbeats) and the [docs](http://toolkits.verticle.io/docs/index.html#integrations-beats) 
* Some major refactorings have been made which also results in non-compatible changes of the instrumentation repo.

Please report bugs first on gitter before opening issues on GH.
