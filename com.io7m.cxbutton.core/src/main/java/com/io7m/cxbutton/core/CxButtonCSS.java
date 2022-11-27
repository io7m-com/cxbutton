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


package com.io7m.cxbutton.core;

import com.io7m.cxbutton.core.internal.CxButtonTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static freemarker.template.Configuration.SQUARE_BRACKET_TAG_SYNTAX;

/**
 * Functions to retrieve CSS for buttons.
 */

public final class CxButtonCSS
{
  private final Configuration configuration;

  private CxButtonCSS(
    final Configuration inConfiguration)
  {
    this.configuration =
      Objects.requireNonNull(inConfiguration, "configuration");
  }

  /**
   * @return A new CSS generator
   */

  public static CxButtonCSS create()
  {
    final Configuration configuration =
      new Configuration(Configuration.VERSION_2_3_31);

    configuration.setTagSyntax(SQUARE_BRACKET_TAG_SYNTAX);
    configuration.setTemplateLoader(new CxButtonTemplateLoader());
    return new CxButtonCSS(configuration);
  }

  /**
   * Generate CSS.
   *
   * @param tag     An optional tag to be appended to CSS classes
   * @param license Print the license header
   * @param colors  The colors
   *
   * @return A CSS text
   *
   * @throws IOException On errors
   */

  public String cssOf(
    final Optional<String> tag,
    final boolean license,
    final CxButtonColors colors)
    throws IOException
  {
    Objects.requireNonNull(colors, "colors");

    try {
      final Template t =
        this.configuration.getTemplate("cxbuttonCss");
      final var writer =
        new StringWriter();

      final var data = new HashMap<String, Object>();
      data.put("colors", colors);
      data.put("tag", tag.orElse(""));
      data.put("license", license);
      t.process(data, writer);

      return writer.toString();
    } catch (final TemplateException e) {
      throw new IOException(e);
    }
  }

  /**
   * @return The default button colors
   */

  public static CxButtonColors defaultColors()
  {
    final var buttonEnabledTextColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonEnabledBorderColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonEnabledBodyColor =
      new CxColor(0.86, 0.86, 0.86);
    final var buttonEnabledEmbossNColor =
      new CxColor(1.0, 1.0, 1.0);
    final var buttonEnabledEmbossEColor =
      new CxColor(1.0, 1.0, 1.0);
    final var buttonEnabledEmbossSColor =
      new CxColor(0.66, 0.66, 0.66);
    final var buttonEnabledEmbossWColor =
      new CxColor(0.66, 0.66, 0.66);

    final var buttonHoverTextColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonHoverBorderColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonHoverBodyColor =
      new CxColor(0.93, 0.93, 0.93);
    final var buttonHoverEmbossNColor =
      new CxColor(1.0, 1.0, 1.0);
    final var buttonHoverEmbossEColor =
      new CxColor(1.0, 1.0, 1.0);
    final var buttonHoverEmbossSColor =
      new CxColor(0.66, 0.66, 0.66);
    final var buttonHoverEmbossWColor =
      new CxColor(0.66, 0.66, 0.66);

    final var buttonPressedTextColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonPressedBorderColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonPressedBodyColor =
      new CxColor(0.86, 0.86, 0.86);
    final var buttonPressedEmbossNColor =
      new CxColor(0.66, 0.66, 0.66);
    final var buttonPressedEmbossEColor =
      new CxColor(0.66, 0.66, 0.66);
    final var buttonPressedEmbossSColor =
      new CxColor(0.8, 0.8, 0.8);
    final var buttonPressedEmbossWColor =
      new CxColor(0.8, 0.8, 0.8);

    final var buttonDisabledBorderColor =
      new CxColor(0.0, 0.0, 0.0);
    final var buttonDisabledBodyColor =
      new CxColor(0.86, 0.86, 0.86);
    final var buttonDisabledTextColor =
      buttonDisabledBodyColor.darker(0.2);

    return new CxButtonColors(
      new CxButtonStateColors(
        buttonEnabledTextColor,
        buttonEnabledBodyColor,
        buttonEnabledBorderColor,
        buttonEnabledEmbossEColor,
        buttonEnabledEmbossNColor,
        buttonEnabledEmbossSColor,
        buttonEnabledEmbossWColor
      ),
      new CxButtonStateColors(
        buttonDisabledTextColor,
        buttonDisabledBodyColor,
        buttonDisabledBorderColor,
        buttonDisabledBodyColor,
        buttonDisabledBodyColor,
        buttonDisabledBodyColor,
        buttonDisabledBodyColor
      ),
      new CxButtonStateColors(
        buttonPressedTextColor,
        buttonPressedBodyColor,
        buttonPressedBorderColor,
        buttonPressedEmbossEColor,
        buttonPressedEmbossNColor,
        buttonPressedEmbossSColor,
        buttonPressedEmbossWColor
      ),
      new CxButtonStateColors(
        buttonHoverTextColor,
        buttonHoverBodyColor,
        buttonHoverBorderColor,
        buttonHoverEmbossEColor,
        buttonHoverEmbossNColor,
        buttonHoverEmbossSColor,
        buttonHoverEmbossWColor
      )
    );
  }

  /**
   * Derive color schemes for the given input values.
   *
   * @param textColor       The text color
   * @param backgroundColor The background color
   * @param borderColor     The border color
   * @param embossFactor    The emboss factor (higher is more severe embossing)
   * @param pressFactor     The amount to darken the button when pressed
   * @param hoverFactor     The amount to brighten the button when hovering
   *
   * @return The default button colors
   */

  public static CxButtonColors colorsForScheme(
    final CxColor textColor,
    final CxColor borderColor,
    final CxColor backgroundColor,
    final double pressFactor,
    final double hoverFactor,
    final double embossFactor)
  {
    final var buttonEnabledEmbossNColor =
      backgroundColor.lighter(embossFactor);
    final var buttonEnabledEmbossEColor =
      backgroundColor.lighter(embossFactor);
    final var buttonEnabledEmbossSColor =
      backgroundColor.darker(embossFactor);
    final var buttonEnabledEmbossWColor =
      backgroundColor.darker(embossFactor);

    final var buttonHoverBodyColor =
      backgroundColor.lighter(hoverFactor);
    final var buttonHoverEmbossNColor =
      buttonHoverBodyColor.lighter(embossFactor);
    final var buttonHoverEmbossEColor =
      buttonHoverBodyColor.lighter(embossFactor);
    final var buttonHoverEmbossSColor =
      buttonHoverBodyColor.darker(embossFactor);
    final var buttonHoverEmbossWColor =
      buttonHoverBodyColor.darker(embossFactor);

    final var buttonPressedBodyColor =
      backgroundColor.darker(pressFactor);
    final var buttonPressedEmbossNColor =
      buttonPressedBodyColor.darker(embossFactor);
    final var buttonPressedEmbossEColor =
      buttonPressedBodyColor.darker(embossFactor);
    final var buttonPressedEmbossSColor =
      buttonPressedBodyColor.lighter(embossFactor);
    final var buttonPressedEmbossWColor =
      buttonPressedBodyColor.lighter(embossFactor);

    final var buttonDisabledTextColor =
      backgroundColor.darker(0.2);

    return new CxButtonColors(
      new CxButtonStateColors(
        textColor,
        backgroundColor,
        borderColor,
        buttonEnabledEmbossEColor,
        buttonEnabledEmbossNColor,
        buttonEnabledEmbossSColor,
        buttonEnabledEmbossWColor
      ),
      new CxButtonStateColors(
        buttonDisabledTextColor,
        backgroundColor,
        borderColor,
        backgroundColor,
        backgroundColor,
        backgroundColor,
        backgroundColor
      ),
      new CxButtonStateColors(
        textColor,
        buttonPressedBodyColor,
        borderColor,
        buttonPressedEmbossEColor,
        buttonPressedEmbossNColor,
        buttonPressedEmbossSColor,
        buttonPressedEmbossWColor
      ),
      new CxButtonStateColors(
        textColor,
        buttonHoverBodyColor,
        borderColor,
        buttonHoverEmbossEColor,
        buttonHoverEmbossNColor,
        buttonHoverEmbossSColor,
        buttonHoverEmbossWColor
      )
    );
  }
}
