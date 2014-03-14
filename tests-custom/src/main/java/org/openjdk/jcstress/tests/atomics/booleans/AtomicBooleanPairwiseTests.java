/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.jcstress.tests.atomics.booleans;

import org.openjdk.jcstress.infra.annotations.Actor;
import org.openjdk.jcstress.infra.annotations.ConcurrencyStressTest;
import org.openjdk.jcstress.infra.annotations.State;
import org.openjdk.jcstress.infra.results.IntResult2;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanPairwiseTests {

    @State
    public static class MyState extends AtomicBoolean {
    }

    @ConcurrencyStressTest
    public static class CAS_CAS {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.compareAndSet(false, true) ? 1 : 0; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.compareAndSet(false, true) ? 1 : 0; }
    }

    @ConcurrencyStressTest
    public static class CAS_GetAndSet {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.compareAndSet(false, true) ? 1 : 0; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.getAndSet(true) ? 0 : 1; }
    }

    @ConcurrencyStressTest
    public static class CAS_WCAS {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.compareAndSet(false, true) ? 1 : 0; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.weakCompareAndSet(false, true) ? 1 : 0; }
    }

    @ConcurrencyStressTest
    public static class GetAndSet_GetAndSet {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.getAndSet(true) ? 0 : 1; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.getAndSet(true) ? 0 : 1; }
    }

    @ConcurrencyStressTest
    public static class GetAndSet_WCAS {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.getAndSet(true) ? 0 : 1; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.weakCompareAndSet(false, true) ? 1 : 0; }
    }

    @ConcurrencyStressTest
    public static class WCAS_WCAS {
        @Actor public void actor1(MyState s, IntResult2 r) { r.r1 = s.weakCompareAndSet(false, true) ? 1 : 0; }
        @Actor public void actor2(MyState s, IntResult2 r) { r.r2 = s.weakCompareAndSet(false, true) ? 1 : 0; }
    }

}
