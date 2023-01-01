/**
 * @test
 * @bug 5045412 6627366
 * @compile -Xlint:serial -XDfailcomplete=java.io.Serializable Bar.java Foo.java
 */
class Bar implements java.io.Serializable {
}
