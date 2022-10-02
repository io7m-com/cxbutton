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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.io7m.dixmont.colors.DmColor;

import java.util.Objects;

/**
 * The colors used for a button state.
 *
 * @param textColor    The button text color
 * @param bodyColor    The button body color
 * @param borderColor  The button border color
 * @param embossEColor The E emboss color
 * @param embossNColor The N emboss color
 * @param embossSColor The S emboss color
 * @param embossWColor The W emboss color
 */

@JsonSerialize
@JsonDeserialize
public record CxButtonStateColors(
  @JsonProperty(value = "TextColor", required = true)
  DmColor textColor,
  @JsonProperty(value = "BodyColor", required = true)
  DmColor bodyColor,
  @JsonProperty(value = "BorderColor", required = true)
  DmColor borderColor,
  @JsonProperty(value = "EmbossEColor", required = true)
  DmColor embossEColor,
  @JsonProperty(value = "EmbossNColor", required = true)
  DmColor embossNColor,
  @JsonProperty(value = "EmbossSColor", required = true)
  DmColor embossSColor,
  @JsonProperty(value = "EmbossWColor", required = true)
  DmColor embossWColor)
{
  /**
   * The colors used for a button state.
   *
   * @param textColor    The button text color
   * @param bodyColor    The button body color
   * @param borderColor  The button border color
   * @param embossEColor The E emboss color
   * @param embossNColor The N emboss color
   * @param embossSColor The S emboss color
   * @param embossWColor The W emboss color
   */

  public CxButtonStateColors
  {
    Objects.requireNonNull(textColor, "buttonTextColor");
    Objects.requireNonNull(bodyColor, "buttonBodyColor");
    Objects.requireNonNull(borderColor, "buttonBorderColor");
    Objects.requireNonNull(embossEColor, "buttonEmbossEColor");
    Objects.requireNonNull(embossNColor, "buttonEmbossNColor");
    Objects.requireNonNull(embossSColor, "buttonEmbossSColor");
    Objects.requireNonNull(embossWColor, "buttonEmbossWColor");
  }
}