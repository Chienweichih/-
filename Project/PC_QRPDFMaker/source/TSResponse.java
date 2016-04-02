/*
002:         * Copyright 2003-2004 Sun Microsystems, Inc.  All Rights Reserved.
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

//        package sun.security.timestamp;

        import java.io.IOException;
        import java.math.BigInteger;
        import sun.security.pkcs.PKCS7;
        import sun.security.pkcs.PKCS9Attribute;
        import sun.security.pkcs.PKCS9Attributes;
        import sun.security.pkcs.ParsingException;
        import sun.security.pkcs.SignerInfo;
        import sun.security.util.DerValue;
        import sun.security.x509.AlgorithmId;
        import sun.security.x509.X500Name;

        /**
040:         * This class provides the response corresponding to a timestamp request,
041:         * as defined in
042:         * <a href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</a>.
043:         *
044:         * The TimeStampResp ASN.1 type has the following definition:
045:         * <pre>
046:         *
047:         *     TimeStampResp ::= SEQUENCE {
048:         *         status            PKIStatusInfo,
049:         *         timeStampToken    TimeStampToken OPTIONAL ]
050:         *
051:         *     PKIStatusInfo ::= SEQUENCE {
052:         *         status        PKIStatus,
053:         *         statusString  PKIFreeText OPTIONAL,
054:         *         failInfo      PKIFailureInfo OPTIONAL }
055:         *
056:         *     PKIStatus ::= INTEGER {
057:         *         granted                (0),
058:         *           -- when the PKIStatus contains the value zero a TimeStampToken, as
059:         *           -- requested, is present.
060:         *         grantedWithMods        (1),
061:         *           -- when the PKIStatus contains the value one a TimeStampToken,
062:         *           -- with modifications, is present.
063:         *         rejection              (2),
064:         *         waiting                (3),
065:         *         revocationWarning      (4),
066:         *           -- this message contains a warning that a revocation is
067:         *           -- imminent
068:         *         revocationNotification (5)
069:         *           -- notification that a revocation has occurred }
070:         *
071:         *     PKIFreeText ::= SEQUENCE SIZE (1..MAX) OF UTF8String
072:         *           -- text encoded as UTF-8 String (note:  each UTF8String SHOULD
073:         *           -- include an RFC 1766 language tag to indicate the language
074:         *           -- of the contained text)
075:         *
076:         *     PKIFailureInfo ::= BIT STRING {
077:         *         badAlg              (0),
078:         *           -- unrecognized or unsupported Algorithm Identifier
079:         *         badRequest          (2),
080:         *           -- transaction not permitted or supported
081:         *         badDataFormat       (5),
082:         *           -- the data submitted has the wrong format
083:         *         timeNotAvailable    (14),
084:         *           -- the TSA's time source is not available
085:         *         unacceptedPolicy    (15),
086:         *           -- the requested TSA policy is not supported by the TSA
087:         *         unacceptedExtension (16),
088:         *           -- the requested extension is not supported by the TSA
089:         *         addInfoNotAvailable (17)
090:         *           -- the additional information requested could not be understood
091:         *           -- or is not available
092:         *         systemFailure       (25)
093:         *           -- the request cannot be handled due to system failure }
094:         *
095:         *     TimeStampToken ::= ContentInfo
096:         *         -- contentType is id-signedData
097:         *         -- content is SignedData
098:         *         -- eContentType within SignedData is id-ct-TSTInfo
099:         *         -- eContent within SignedData is TSTInfo
100:         *
101:         * </pre>
102:         *
103:         * @since 1.5
104:         * @version 1.10, 05/05/07
105:         * @author Vincent Ryan
106:         * @see Timestamper
107:         */

        public class TSResponse {

            // Status codes (from RFC 3161)

            /**
114:             * The requested timestamp was granted.
115:             */
            public static final int GRANTED = 0;

            /**
119:             * The requested timestamp was granted with some modifications.
120:             */
           public static final int GRANTED_WITH_MODS = 1;

            /**
124:             * The requested timestamp was not granted.
125:             */
            public static final int REJECTION = 2;

            /**
129:             * The requested timestamp has not yet been processed.
130:             */
            public static final int WAITING = 3;

            /**
134:             * A warning that a certificate revocation is imminent.
135:             */
            public static final int REVOCATION_WARNING = 4;

            /**
39:             * Notification that a certificate revocation has occurred.
140:             */
            public static final int REVOCATION_NOTIFICATION = 5;
            // Failure codes (from RFC 3161)
            /**
46:             * Unrecognized or unsupported algorithm identifier.
147:             */
            public static final int BAD_ALG = 0;

            /**
151:             * The requested transaction is not permitted or supported.
152:             */
            public static final int BAD_REQUEST = 2;

            /**
156:             * The data submitted has the wrong format.
157:             */
            public static final int BAD_DATA_FORMAT = 5;

            /**
161:             * The TSA's time source is not available.
162:             */
            public static final int TIME_NOT_AVAILABLE = 14;

            /**
166:             * The requested TSA policy is not supported by the TSA.
167:             */
            public static final int UNACCEPTED_POLICY = 15;

            /**
171:             * The requested extension is not supported by the TSA.
172:             */
            public static final int UNACCEPTED_EXTENSION = 16;

            /**
176:             * The additional information requested could not be understood or is not
177:             * available.
178:             */
            public static final int ADD_INFO_NOT_AVAILABLE = 17;

            /**
182:             * The request cannot be handled due to system failure.
183:             */
            public static final int SYSTEM_FAILURE = 25;

            private static final boolean DEBUG = false;

            private int status;

            private String[] statusString = null;

            private int failureInfo = -1;

            private byte[] encodedTsToken = null;

            private PKCS7 tsToken = null;

            /**
99:             * Constructs an object to store the response to a timestamp request.
200:             *
201:             * @param status A buffer containing the ASN.1 BER encoded response.
202:             * @throws IOException The exception is thrown if a problem is encountered
203:             *         parsing the timestamp response.
204:             */
            TSResponse(byte[] tsReply) throws IOException {
                parse(tsReply);
            }

            /**
210:             * Retrieve the status code returned by the TSA.
211:             */
            public int getStatusCode() {
                return status;
            }

            /**
17:             * Retrieve the status messages returned by the TSA.
218:             *
219:             * @return If null then no status messages were received.
220:             */
            public String[] getStatusMessages() {
                return statusString;
            }

            /**
6:             * Retrieve the failure code returned by the TSA.
227:             *
228:             * @return If -1 then no failure code was received.
229:             */
            public int getFailureCode() {
                return failureInfo;
            }

            public String getStatusCodeAsText() {

                switch (status) {
                case GRANTED:
                    return "the timestamp request was granted.";

                case GRANTED_WITH_MODS:
                    return "the timestamp request was granted with some modifications.";

                case REJECTION:
                    return "the timestamp request was rejected.";

                case WAITING:
                    return "the timestamp request has not yet been processed.";

                case REVOCATION_WARNING:
                    return "warning: a certificate revocation is imminent.";

                case REVOCATION_NOTIFICATION:
                    return "notification: a certificate revocation has occurred.";

                default:
                    return ("unknown status code " + status + ".");
                }
            }

            public String getFailureCodeAsText() {

                if (failureInfo == -1) {
                    return null;
                }

                switch (failureInfo) {

                case BAD_ALG:
                    return "Unrecognized or unsupported alrorithm identifier.";

                case BAD_REQUEST:
                    return "The requested transaction is not permitted or supported.";

               case BAD_DATA_FORMAT:
                    return "The data submitted has the wrong format.";

                case TIME_NOT_AVAILABLE:
                    return "The TSA's time source is not available.";

                case UNACCEPTED_POLICY:
                    return "The requested TSA policy is not supported by the TSA.";

                case UNACCEPTED_EXTENSION:
                    return "The requested extension is not supported by the TSA.";

                case ADD_INFO_NOT_AVAILABLE:
                    return "The additional information requested could not be "
                            + "understood or is not available.";

                case SYSTEM_FAILURE:
                    return "The request cannot be handled due to system failure.";

                default:
                    return ("unknown status code " + status);
                }
            }

            /**
:             * Retrieve the timestamp token returned by the TSA.
300:             *
301:             * @return If null then no token was received.
302:             */
            public PKCS7 getToken() {
                return tsToken;
            }

            /**
308:             * Retrieve the ASN.1 BER encoded timestamp token returned by the TSA.
309:             *
310:             * @return If null then no token was received.
311:             */
            public byte[] getEncodedToken() {
                return encodedTsToken;
            }

            /*
317:             * Parses the timestamp response.
318:             *
319:             * @param status A buffer containing the ASN.1 BER encoded response.
320:             * @throws IOException The exception is thrown if a problem is encountered
321:             *         parsing the timestamp response.
322:             */
            private void parse(byte[] tsReply) throws IOException {
                // Decode TimeStampResp

                DerValue derValue = new DerValue(tsReply);
                if (derValue.tag != DerValue.tag_Sequence) {
                    throw new IOException("Bad encoding for timestamp response");
                }

                // Parse status

                DerValue status = derValue.data.getDerValue();
                // Parse status
                this .status = status.data.getInteger();
                if (DEBUG) {
                    System.out.println("timestamp response: status="
                            + this .status);
                }
                // Parse statusString, if present
                if (status.data.available() > 0) {
                    DerValue[] strings = status.data.getSequence(1);
                    statusString = new String[strings.length];
                    for (int i = 0; i < strings.length; i++) {
                        statusString[i] = strings[i].data.getUTF8String();
                    }
                }
                // Parse failInfo, if present
                if (status.data.available() > 0) {
                    byte[] failInfo = status.data.getBitString();
                    int failureInfo = (new Byte(failInfo[0])).intValue();
                    if (failureInfo < 0 || failureInfo > 25
                            || failInfo.length != 1) {
                        throw new IOException(
                                "Bad encoding for timestamp response: "
                                        + "unrecognized value for the failInfo element");
                    }
                    this .failureInfo = failureInfo;
                }

                // Parse timeStampToken, if present
                if (derValue.data.available() > 0) {
                    DerValue timestampToken = derValue.data.getDerValue();
                   encodedTsToken = timestampToken.toByteArray();
                    tsToken = new PKCS7(encodedTsToken);
               }

                // Check the format of the timestamp response
                if (this .status == 0 || this .status == 1) {
                    if (tsToken == null) {
                        throw new TimestampException(
                                "Bad encoding for timestamp response: "
                                        + "expected a timeStampToken element to be present");
                    }
                } else if (tsToken != null) {
                    throw new TimestampException(
                            "Bad encoding for timestamp response: "
                                   + "expected no timeStampToken element to be present");
               }
            }

            final static class TimestampException extends IOException {
                TimestampException(String message) {
                    super(message);
                }
            }
        }
