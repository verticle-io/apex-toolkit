---
layout: post
title: "APEX toolkit launched"
date: 2016-10-31
---

When first discussing and prototyping APEX we were looking to establish a better monitoring of our Java based applications.
Too often we have seen software and projects getting out of control because nobody could get a clue what was really going on inside in detail.
Reading the logs was not sufficient because the information we looked for was like searching the needle in the haystack - or worse - it simply was not put out.

As a result our most important KPI - Mean Time to Resolution (MTTR) - was simply too high because of low visibility caused by ineffective tooling.

Our finding was:

* You cannot get aware of a problem if it is not visible.
* Without awareness you have no chance to react proactively.
* Without the ability to act you make your customers ring the bell first.

Of course you can add debug and logging code, use JMX, btrace and other tools.
And there are quite obvious methods like remote debugging the JVM - surely a good choice but not applicable everywhere.
But we were searching for a more convenient way.

This was our first leap towards APEX - establishing a highly adaptable monitoring tool.
After lots of talk we discovered that we have had created something more generic than we realized and were touching multiple scenarios of interest: runtime monitoring, debugging, advanced testing and business analysis.

We decided to take a step back from our initial intent and refactor and share the APEX toolkit components on Github so others can benefit.
In the future we plan to develop services that are based on this toolkit.
