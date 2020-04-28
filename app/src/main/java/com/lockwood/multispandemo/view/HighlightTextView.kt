package com.lockwood.multispandemo.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import android.util.Log
import com.lockwood.multispan.extensions.fetchAttrs
import com.lockwood.multispan.extensions.getStringOrEmpty
import com.lockwood.multispan.extensions.getTextColorOrDefault
import com.lockwood.multispan.font.FontTwoSpanView
import com.lockwood.multispan.font.span.FontSpan
import com.lockwood.multispandemo.R
import io.github.inflationx.calligraphy3.TypefaceUtils
import java.util.regex.PatternSyntaxException

@SuppressLint("Recycle")
class HighlightTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FontTwoSpanView(context, attrs) {

    companion object {

        private const val TAG = "HighlightTextView"

        private const val DEFAULT_IS_HIGHLIGHT = true
    }

    private var isHighlight: Boolean = DEFAULT_IS_HIGHLIGHT
    private var highlightFont: Typeface = typeface
    private var highlightSpanColor: Int = currentTextColor
    private var highlightPattern: String? = null
        set(value) {
            field = value
            if (field != null) {
                updateSpanStyles()
            }
        }

    init {
        fetchAttrs(R.styleable.HighlightTextView, context, attrs) {
            highlightPattern =
                getStringOrEmpty(R.styleable.HighlightTextView_highlightPattern)
            highlightFont =
                getFontOrDefault(R.styleable.HighlightTextView_highlightFont)
            highlightSpanColor =
                getTextColorOrDefault(
                    R.styleable.HighlightTextView_highlightColor,
                    defaultTextColor
                )
            isHighlight = getBoolean(
                R.styleable.HighlightTextView_isHighlight,
                DEFAULT_IS_HIGHLIGHT
            )
        }
        updateSpanStyles()
    }

    override fun fetchFontFromAssets(path: String): Typeface {
        return try {
            TypefaceUtils.load(context.assets, path)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            typeface
        }
    }

    override fun setSpanOnResult(resultSpans: SpannableString): CharSequence {
        return if (isHighlight && !highlightPattern.isNullOrEmpty()) {
            try {
                val regexPattern = highlightPattern!!.toRegex()
                val highlightMatches = regexPattern.findAll(resultSpans).map { it.value }
                highlightMatches.forEach { item ->
                    val start = resultSpans.indexOf(item)
                    val end = start + item.length
                    resultSpans.setSpan(
                        FontSpan(highlightFont, textSize, highlightSpanColor),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                resultSpans
            } catch (e: PatternSyntaxException) {
                Log.e(TAG, e.message.toString())
                super.setSpanOnResult(resultSpans)
            }
        } else {
            super.setSpanOnResult(resultSpans)
        }
    }

}