package multiknapsack;

/**La clase clock permite medir el tiempo que tarda un programa en ejecutar
 * 
 * @author Miguel Jiménez Gomis
 * @date 6/3/2018
 *
 */
public class Clock {
  long initialTime;     //Tiempo en el que se establece el contador 
  long finalTime;     //Tiempo en el que se detiene el contador
  
  final static double TOMILLISECONDS = 0.000001;    //constante de conversion de nanosegundos a milisegundos
  
 /** Retorna el tiempo inicial en milisegundos
 * @return long
 */
public long getInitialTime() {
  return initialTime;
 } 

 /** Establece el tiempo inicial
 * @param initialTime milisegundos
 */
public void setInitialTime(long initialTime) {
  this.initialTime = initialTime;
 }

 /** Retorna el Tiempo final en milisegundos
 * @return long
 */
public long getFinalTime() {
  return finalTime;
 }

 /** Establece el tiempo final en milisegundos
 * @param finalTime
 */
public void setFinalTime(long finalTime) {
  this.finalTime = finalTime;
 }

 /**Establece el tiempo inicial del contador al tiempo actual del sistema
 * 
 */
public void start(){
   long dummy = Math.round(System.nanoTime() * TOMILLISECONDS);
   this.setInitialTime(dummy);
  }
  
  /**  Establece el tiempo final del contador al tiempo final del sistema
 * 
 */
public void stop(){
    long dummy = Math.round(System.nanoTime() * TOMILLISECONDS);
    this.setFinalTime(dummy);
  }
  
  /** Retorna el tiempo capturado por el contador en milisegundos
 * @return long
 */
public long getElapsedTime(){
    return (this.getFinalTime() - this.getInitialTime());
  }
}