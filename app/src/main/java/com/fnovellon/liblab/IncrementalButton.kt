package com.fnovellon.liblab

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


class IncrementalButton : LinearLayout {

    var counter: Int = 0
        set(value) {
            tvCount.text = value.toString()
            field = value
        }

    private var maxCount: Int = Int.MAX_VALUE
    private var minCount: Int = Int.MIN_VALUE
    private var stepCount: Int = 1
    private var initCount: Int = 0

    private val tvCount: TextView
    private val btSub: Button
    private val btAdd: Button

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeAttributes(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        initializeAttributes(attrs)
    }

    init {
        inflate(context, R.layout.custom_incremental_button, this)

        tvCount = findViewById(R.id.tv_count)
        btSub = findViewById(R.id.bt_sub)
        btAdd = findViewById(R.id.bt_add)

        tvCount.text = initCount.toString()
        btSub.setOnClickListener {
            val newCount = counter - stepCount
            if (newCount >= minCount) {
                counter = newCount
            }
        }
        btAdd.setOnClickListener {
            val newCount = counter + stepCount
            if (newCount <= maxCount) {
                counter = newCount
            }
        }

        print(
            """
            ---------------------------------------
            ---------------------------------------
            ---------------------------------------
            ---------------------------------------
                            INIT
            ---------------------------------------
            ---------------------------------------
            ---------------------------------------
            ---------------------------------------
        """.trimIndent()
        )
    }

    private fun initializeAttributes(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.IncrementalButton)

        maxCount = attributes.getInt(R.styleable.IncrementalButton_maxCount, Int.MAX_VALUE)
        minCount = attributes.getInt(R.styleable.IncrementalButton_minCount, Int.MIN_VALUE)

        if (minCount > maxCount) {
            throw IllegalArgumentException("minCount > maxCount : $minCount > $maxCount")
        }

        stepCount = attributes.getInt(R.styleable.IncrementalButton_stepCount, 1)
        initCount = attributes.getInt(R.styleable.IncrementalButton_initCount, 0)

    }



}

