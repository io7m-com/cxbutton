cxbutton
===

[![Maven Central](https://img.shields.io/maven-central/v/com.io7m.cxbutton/com.io7m.cxbutton.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.io7m.cxbutton%22)
[![Maven Central (snapshot)](https://img.shields.io/nexus/s/com.io7m.cxbutton/com.io7m.cxbutton?server=https%3A%2F%2Fs01.oss.sonatype.org&style=flat-square)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/io7m/cxbutton/)
[![Codecov](https://img.shields.io/codecov/c/github/io7m-com/cxbutton.svg?style=flat-square)](https://codecov.io/gh/io7m-com/cxbutton)
![Java Version](https://img.shields.io/badge/21-java?label=java&color=e6c35c)

![com.io7m.cxbutton](./src/site/resources/cxbutton.jpg?raw=true)

| JVM | Platform | Status |
|-----|----------|--------|
| OpenJDK (Temurin) Current | Linux | [![Build (OpenJDK (Temurin) Current, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/cxbutton/main.linux.temurin.current.yml)](https://www.github.com/io7m-com/cxbutton/actions?query=workflow%3Amain.linux.temurin.current)|
| OpenJDK (Temurin) LTS | Linux | [![Build (OpenJDK (Temurin) LTS, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/cxbutton/main.linux.temurin.lts.yml)](https://www.github.com/io7m-com/cxbutton/actions?query=workflow%3Amain.linux.temurin.lts)|
| OpenJDK (Temurin) Current | Windows | [![Build (OpenJDK (Temurin) Current, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/cxbutton/main.windows.temurin.current.yml)](https://www.github.com/io7m-com/cxbutton/actions?query=workflow%3Amain.windows.temurin.current)|
| OpenJDK (Temurin) LTS | Windows | [![Build (OpenJDK (Temurin) LTS, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/cxbutton/main.windows.temurin.lts.yml)](https://www.github.com/io7m-com/cxbutton/actions?query=workflow%3Amain.windows.temurin.lts)|

## cxbutton

Embossed CSS buttons.

### Features

* Templated, customizable CSS buttons.
* [OSGi](https://www.osgi.org/) ready.
* [JPMS](https://en.wikipedia.org/wiki/Java_Platform_Module_System) ready.
* ISC license.
* High-coverage automated test suite.

### Usage

Use the `CxButtonCSS` class to generate CSS text.

```
final CxButtonCSS css =
  CxButtonCSS.create();

final String text =
  css.cssOf(Optional.empty(), false, CxButtonCSS.defaultColors());
```

It's possible to specify custom colors, and the package can automatically
derive the correct color variations for embossing:

```
final String text =
  css.cssOf(
    Optional.of("Ocean"),
    true,
    CxButtonCSS.colorsForScheme(
      new CxColor(1.0, 1.0, 1.0),
      new CxColor(0.0, 0.0, 0.0),
      new CxColor(0.105, 0.313, 0.454),
      0.2,
      0.2,
      0.5
    )
  );
```

