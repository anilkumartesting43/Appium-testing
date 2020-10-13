package WingsMavenExp.WingsMavenexp.service.rest.protobuf

import java.io.InputStream
import java.io.OutputStream
import java.lang.annotation.Annotation
import java.lang.reflect.Type
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.Provider

import com.google.protobuf.Message
import WingsMavenExp.WingsMavenexp.service.api.transport.Transportables

@Provider
@Consumes(Array(MediaType.APPLICATION_OCTET_STREAM))
@Produces(Array(MediaType.APPLICATION_OCTET_STREAM))
class ProtocolBufferBinaryMessageBodyProvider extends ProtocolBufferMessageBodyProviderIntf {

  override def readFrom
  (clazz: Class[Message],
   genericType: Type,
   annotations: Array[Annotation],
   mediaType: MediaType,
   httpHeaders: MultivaluedMap[String, String],
   entityStream: InputStream): Message =
    try { Transportables.readBinary(clazz, entityStream) }
    catch { case exception: Exception => throw new WebApplicationException(exception) }

  override def writeTo
  (message: Message,
   `type`: Class[_],
   genericType: Type,
   annotations: Array[Annotation],
   mediaType: MediaType,
   httpHeaders: MultivaluedMap[String, AnyRef],
   entityStream: OutputStream): Unit =
    Transportables.writeBinary(message, entityStream)

}
