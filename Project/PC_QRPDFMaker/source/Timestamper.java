/*
02:         * Copyright 2003 Sun Microsystems, Inc.  All Rights Reserved.
03:         * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
04:         *
05:         * This code is free software; you can redistribute it and/or modify it
06:         * under the terms of the GNU General Public License version 2 only, as
07:         * published by the Free Software Foundation.  Sun designates this
08:         * particular file as subject to the "Classpath" exception as provided
09:         * by Sun in the LICENSE file that accompanied this code.
10:         *
11:         * This code is distributed in the hope that it will be useful, but WITHOUT
12:         * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
13:         * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
14:         * version 2 for more details (a copy is included in the LICENSE file that
15:         * accompanied this code).
16:         *
17:         * You should have received a copy of the GNU General Public License version
18:         * 2 along with this work; if not, write to the Free Software Foundation,
19:         * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
20:         *
21:         * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
22:         * CA 95054 USA or visit www.sun.com if you need additional information or
23:         * have any questions.
24:         */

//        package sun.security.timestamp;
        
        import java.io.IOException;

        /**
31:         * A timestamping service which conforms to the Time-Stamp Protocol (TSP)
32:         * defined in:
33:         * <a href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</a>.
34:         * Individual timestampers may communicate with a Timestamping Authority (TSA)
35:         * over different transport machanisms. TSP permits at least the following
36:         * transports: HTTP, Internet mail, file-based and socket-based.
37:         *
38:         * @version 1.9, 05/05/07
39:         * @author Vincent Ryan
40:         * @see HttpTimestamper
41:         */
        public interface Timestamper {

            /*
45:             * Connects to the TSA and requests a timestamp.
46:             *
47:             * @param tsQuery The timestamp query.
48:             * @return The result of the timestamp query.
49:             * @throws IOException The exception is thrown if a problem occurs while
50:             *         communicating with the TSA.
51:             */
            public TSResponse generateTimestamp(TSRequest tsQuery)
                    throws IOException;
        }
