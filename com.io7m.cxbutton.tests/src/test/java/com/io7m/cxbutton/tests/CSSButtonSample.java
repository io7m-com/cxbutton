/*
 * Copyright © 2022 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */


package com.io7m.cxbutton.tests;

import com.io7m.cxbutton.core.CxButtonCSS;
import com.io7m.dixmont.colors.DmColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public final class CSSButtonSample
{
  private CSSButtonSample()
  {
  }

  public static void main(
    final String[] args)
    throws IOException
  {
    if (args.length != 1) {
      throw new IllegalArgumentException("Usage: directory");
    }

    final var output =
      Paths.get(args[0]);
    final var cssOut0 =
      output.resolve("sample.css");
    final var cssOut1 =
      output.resolve("cxbutton.css");
    final var xhtmlOut =
      output.resolve("sample.xhtml");

    try (var out =
           Files.newOutputStream(cssOut0, TRUNCATE_EXISTING, CREATE)) {
      try (var in =
             CSSButtonSample.class.getResourceAsStream(
               "/com/io7m/cxbutton/tests/sample.css")) {
        in.transferTo(out);
      }
    }

    try (var out =
           Files.newOutputStream(xhtmlOut, TRUNCATE_EXISTING, CREATE)) {
      try (var in =
             CSSButtonSample.class.getResourceAsStream(
               "/com/io7m/cxbutton/tests/sample.xhtml")) {
        in.transferTo(out);
      }
    }

    try (var out =
           Files.newBufferedWriter(cssOut1, TRUNCATE_EXISTING, CREATE)) {
      final var cssOcean =
        CxButtonCSS.colorsForScheme(
          new DmColor(1.0, 1.0, 1.0),
          new DmColor(0.0, 0.0, 0.0),
          new DmColor(0.105, 0.313, 0.454),
          0.2,
          0.2,
          0.5
        );
      final var cssForest =
        CxButtonCSS.colorsForScheme(
          new DmColor(1.0, 1.0, 1.0),
          new DmColor(0.0, 0.0, 0.0),
          new DmColor(0.105, 0.454, 0.313),
          0.2,
          0.2,
          0.5
        );
      final var cssDesert =
        CxButtonCSS.colorsForScheme(
          new DmColor(1.0, 1.0, 1.0),
          new DmColor(0.0, 0.0, 0.0),
          new DmColor(0.454,  0.313, 0.105),
          0.2,
          0.2,
          0.5
        );
      final var cssRoad =
        CxButtonCSS.colorsForScheme(
          new DmColor(1.0, 1.0, 1.0),
          new DmColor(0.0, 0.0, 0.0),
          new DmColor(0.5,  0.5, 0.5),
          0.2,
          0.2,
          0.5
        );

      final var css = CxButtonCSS.create();
      out.append(css.cssOf(Optional.of("Ocean"), true, cssOcean));
      out.append("\n");
      out.append(css.cssOf(Optional.of("Forest"), false, cssForest));
      out.append("\n");
      out.append(css.cssOf(Optional.of("Desert"), false, cssDesert));
      out.append("\n");
      out.append(css.cssOf(Optional.of("Road"), false, cssRoad));
      out.append("\n");
    }
  }
}
