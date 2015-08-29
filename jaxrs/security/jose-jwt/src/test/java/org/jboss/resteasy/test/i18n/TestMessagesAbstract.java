package org.jboss.resteasy.test.i18n;

import java.util.Locale;

import junit.framework.Assert;

import org.jboss.resteasy.jose.i18n.Messages;
import org.jboss.resteasy.jose.jwe.CompressionAlgorithm;
import org.jboss.resteasy.jose.jwe.EncryptionMethod;
import org.jboss.resteasy.test.resteasy_jaxrs.i18n.TestMessagesParent;
import org.junit.Test;

/**
 * 
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 * Copyright Aug 28, 2015
 */
abstract public class TestMessagesAbstract extends TestMessagesParent
{
   protected static final String BASE = String.format("0%5s", Messages.BASE).substring(0, 4);
   protected static final String BASE3 = BASE.substring(0, 3);

   @Test
   public void testLocale() throws Exception
   {  
      Locale locale = getLocale();
      String filename = "org/jboss/resteasy/jose/i18n/Messages.i18n_" + locale.toString() + ".properties";
      if (!before(locale, filename))
      {
         System.out.println(getClass() + ": " + filename + " not found.");
         return;
      }
      
      Assert.assertEquals(getExpected(BASE + "00", "algorithmOfSharedSymmetricKey"), Messages.MESSAGES.algorithmOfSharedSymmetricKey());
      Assert.assertEquals(getExpected(BASE + "15", "cekKeyLengthMismatch", 3, 7), Messages.MESSAGES.cekKeyLengthMismatch(3, 7));
      Assert.assertEquals(getExpected(BASE + "25", "contentEncryptionKeyLength", 11, EncryptionMethod.A256GCM), Messages.MESSAGES.contentEncryptionKeyLength(11, EncryptionMethod.A256GCM));
      Assert.assertEquals(getExpected(BASE3 + "655", "unsupportedCompressionAlgorithm", CompressionAlgorithm.DEF), Messages.MESSAGES.unsupportedCompressionAlgorithm(CompressionAlgorithm.DEF));
      Assert.assertEquals(getExpected(BASE3 + "675", "unsupportedKeyLength"), Messages.MESSAGES.unsupportedKeyLength());
   }
   
   @Override
   protected int getExpectedNumberOfMethods()
   {
      return Messages.class.getDeclaredMethods().length;  
   }
   
   abstract protected Locale getLocale();
}
