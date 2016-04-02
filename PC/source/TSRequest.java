   /*
002:         * Copyright 2003 Sun Microsystems, Inc.  All Rights Reserved.
003:         * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
004:         *
005:         * This code is free software; you can redistribute it and/or modify it
006:         * under the terms of the GNU General Public License version 2 only, as
007:         * published by the Free Software Foundation.  Sun designates this
008:         * particular file as subject to the "Classpath" exception as provided
009:         * by Sun in the LICENSE file that accompanied this code.
010:         *
011:         * This code is distributed in the hope that it will be useful, but WITHOUT
012:         * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
013:         * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
014:         * version 2 for more details (a copy is included in the LICENSE file that
015:         * accompanied this code).
016:         *
017:         * You should have received a copy of the GNU General Public License version
018:         * 2 along with this work; if not, write to the Free Software Foundation,
019:         * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
020:         *
021:         * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
022:         * CA 95054 USA or visit www.sun.com if you need additional information or
023:         * have any questions.
024:         */

//       package sun.security.timestamp;

        import java.io.IOException;
        import java.math.BigInteger;
        import java.security.cert.X509Extension;
        import sun.security.util.DerValue;
        import sun.security.util.DerOutputStream;
        import sun.security.util.ObjectIdentifier;

        /**
036:         * This class provides a timestamp request, as defined in
037:         * <a href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</a>.
038:         *
039:         * The TimeStampReq ASN.1 type has the following definition:
040:         * <pre>
041:         *
042:         *     TimeStampReq ::= SEQUENCE {
043:         *         version           INTEGER { v1(1) },
044:         *         messageImprint    MessageImprint
045:         *           -- a hash algorithm OID and the hash value of the data to be
046:         *           -- time-stamped.
047:         *         reqPolicy         TSAPolicyId    OPTIONAL,
048:         *         nonce             INTEGER        OPTIONAL,
049:         *         certReq           BOOLEAN        DEFAULT FALSE,
050:         *         extensions        [0] IMPLICIT Extensions OPTIONAL }
051:         *
052:         *     MessageImprint ::= SEQUENCE {
053:         *         hashAlgorithm     AlgorithmIdentifier,
054:         *         hashedMessage     OCTET STRING }
055:         *
056:         *     TSAPolicyId ::= OBJECT IDENTIFIER
057:         *
058:         * </pre>
059:         *
060:         * @since 1.5
061:         * @version 1.9, 05/05/07
062:         * @author Vincent Ryan
063:         * @see Timestamper
064:         */

        public class TSRequest {

            private static final ObjectIdentifier SHA1_OID;
            private static final ObjectIdentifier MD5_OID;
           static {
                ObjectIdentifier sha1 = null;
                ObjectIdentifier md5 = null;
                try {
                    sha1 = new ObjectIdentifier("1.3.14.3.2.26");
                    md5 = new ObjectIdentifier("1.2.840.113549.2.5");
                } catch (IOException ioe) {
                    // should not happen
                }
                SHA1_OID = sha1;
                MD5_OID = md5;
            }

            private int version = 1;

            private ObjectIdentifier hashAlgorithmId = null;

            private byte[] hashValue;

            private String policyId = null;

            private BigInteger nonce = null;

            private boolean returnCertificate = false;

            private X509Extension[] extensions = null;

            /**
098:             * Constructs a timestamp request for the supplied hash value..
099:             *
100:             * @param hashValue     The hash value. This is the data to be timestamped.
101:             * @param hashAlgorithm The name of the hash algorithm.
102:             */
            public TSRequest(byte[] hashValue, String hashAlgorithm) {

                // Check the common hash algorithms
                if ("MD5".equalsIgnoreCase(hashAlgorithm)) {
                    hashAlgorithmId = MD5_OID;
                    // Check that the hash value matches the hash algorithm
                    assert hashValue.length == 16;
               } else if ("SHA-1".equalsIgnoreCase(hashAlgorithm)
                        || "SHA".equalsIgnoreCase(hashAlgorithm)
                        || "SHA1".equalsIgnoreCase(hashAlgorithm)) {
                    hashAlgorithmId = SHA1_OID;
                    // Check that the hash value matches the hash algorithm
                    assert hashValue.length == 20;

                }
                // Clone the hash value
                this .hashValue = new byte[hashValue.length];
                System.arraycopy(hashValue, 0, this .hashValue, 0,
                        hashValue.length);
          }

           /**
126:             * Sets the Time-Stamp Protocol version.
127:             *
128:             * @param version The TSP version.
129:             */
            public void setVersion(int version) {
                this .version = version;
            }

            /**
135:             * Sets an object identifier for the Time-Stamp Protocol policy.
136:             *
137:             * @param version The policy object identifier.
138:             */
            public void setPolicyId(String policyId) {
                this .policyId = policyId;
            }

            /**
144:             * Sets a nonce.
145:             * A nonce is a single-use random number.
146:             *
147:             * @param nonce The nonce value.
148:             */
            public void setNonce(BigInteger nonce) {
                this .nonce = nonce;
            }

            /**
154:             * Request that the TSA include its signing certificate in the response.
155:             *
156:             * @param returnCertificate True if the TSA should return its signing
157:             *                          certificate. By default it is not returned.
158:             */
            public void requestCertificate(boolean returnCertificate) {
                this .returnCertificate = returnCertificate;
            }

            /**
164:             * Sets the Time-Stamp Protocol extensions.
165:             *
166:             * @param extensions The protocol extensions.
             */
            public void setExtensions(X509Extension[] extensions) {
                this .extensions = extensions;
            }

           public byte[] encode() throws IOException {

                DerOutputStream request = new DerOutputStream();

                // encode version
                request.putInteger(version);

                // encode messageImprint
               DerOutputStream messageImprint = new DerOutputStream();
                DerOutputStream hashAlgorithm = new DerOutputStream();
                hashAlgorithm.putOID(hashAlgorithmId);
               messageImprint.write(DerValue.tag_Sequence, hashAlgorithm);
               messageImprint.putOctetString(hashValue);
                request.write(DerValue.tag_Sequence, messageImprint);

                // encode optional elements

                if (policyId != null) {
                    request.putOID(new ObjectIdentifier(policyId));
                }

                if (nonce != null) {
                    request.putInteger(nonce);
                }

                if (returnCertificate) {
                    request.putBoolean(true);
                }

                DerOutputStream out = new DerOutputStream();
                out.write(DerValue.tag_Sequence, request);
                return out.toByteArray();
            }
        }
