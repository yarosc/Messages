package test.muzz.all.extensions

fun <T> List<T>.penultimate(): T = this[this.lastIndex - 1]
