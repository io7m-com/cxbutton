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
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CxButtonCSSTest
{
  private static final Logger LOG =
    LoggerFactory.getLogger(CxButtonCSSTest.class);

  /**
   * CSS works.
   *
   * @throws IOException On errors
   */

  @Test
  public void testCSS()
    throws IOException
  {
    final var css =
      CxButtonCSS.create();
    final var text =
      css.cssOf(Optional.empty(), true, CxButtonCSS.defaultColors());

    LOG.debug("{}", text);
    assertTrue(text.contains(".cxbutton:hover"));
  }

  /**
   * The license header can be turned off.
   *
   * @throws IOException On errors
   */

  @Test
  public void testCSSNoLicense()
    throws IOException
  {
    final var css =
      CxButtonCSS.create();
    final var text =
      css.cssOf(Optional.empty(), false, CxButtonCSS.defaultColors());

    LOG.debug("{}", text);
    assertFalse(text.contains("Copyright"));
  }

  /**
   * Appending a tag to CSS works.
   *
   * @throws IOException On errors
   */

  @Test
  public void testCSSTag()
    throws IOException
  {
    final var css =
      CxButtonCSS.create();
    final var text =
      css.cssOf(Optional.of("TAG"), true, CxButtonCSS.defaultColors());

    LOG.debug("{}", text);
    assertTrue(text.contains(".cxbuttonTAG"));
  }

  /**
   * The blue color scheme works.
   *
   * @throws IOException On errors
   */

  @Test
  public void testCSSBlue()
    throws IOException
  {
    final var css =
      CxButtonCSS.create();
    final var text =
      css.cssOf(
        Optional.of("Ocean"),
        true,
        CxButtonCSS.colorsForScheme(
          new DmColor(1.0, 1.0, 1.0),
          new DmColor(0.0, 0.0, 0.0),
          new DmColor(0.105, 0.313, 0.454),
          0.2,
          0.2,
          0.5
        )
      );

    LOG.debug("{}", text);
    assertTrue(text.contains(".cxbuttonOcean:hover"));
  }
}
