package com.cheezycode.randomquote.models

data class Notification(
    var hour: Int,
    var minutes: Int,
    var quantity: Int,
    var checked: Boolean,
    var timeMode: String
)
