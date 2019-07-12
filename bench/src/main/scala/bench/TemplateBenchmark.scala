package bench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import scala.collection.mutable.ListBuffer

@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = Array(
  "-server",
  "-Xms2g",
  "-Xmx2g",
  "-XX:NewSize=1g",
  "-XX:MaxNewSize=1g",
  "-XX:InitialCodeCacheSize=512m",
  "-XX:ReservedCodeCacheSize=512m",
  "-XX:+UseParallelGC",
  "-XX:-UseBiasedLocking",
  "-XX:+AlwaysPreTouch"))
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class ListBufferBenchmark {
  @Param(Array("1", "10", "100"))
  var size: Int = 1000

  @Benchmark
  def intListCreation: List[Int] = {
    val squares = new ListBuffer[Int]()
    var i = 0
    val l = size
    while (i < l) {
      squares += i * i
      i += 1
    }
    squares.toList
  }
}
