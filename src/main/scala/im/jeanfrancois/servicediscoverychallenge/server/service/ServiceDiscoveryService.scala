package im.jeanfrancois.servicediscoverychallenge.server.service

import com.google.inject.{Singleton, Inject}
import im.jeanfrancois.servicediscoverychallenge.server._

/**
 * Document me!
 *
 * @author jfim
 */

@Singleton
class ServiceDiscoveryService @Inject() (val serviceRegistry : ServiceRegistry) extends Service {
  @ServiceMethod
  def listServices() : List[String] = {
    serviceRegistry.serviceList.map(service => service.getClass.toString)
  }

  @ServiceMethod
  def listServiceMethods(classname : String) : List[String] = {
    serviceRegistry.serviceList.find(service => service.getClass.toString == classname) match {
      case Some(service) => {
        service.serviceMethods.map(method => method.getName + ":" + method.getReturnType + ":" + method.getParameterTypes.toList.foldLeft("")((str, param) => str + "," + param.getName));
      }
      case None => {
        Nil
      }
    }
  }
}