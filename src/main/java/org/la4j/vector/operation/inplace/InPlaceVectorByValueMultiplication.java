/*
 * Copyright 2011-2014, by Vladimir Kostyukov and Contributors.
 *
 * This file is part of la4j project (http://la4j.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributor(s): -
 *
 */

package org.la4j.vector.operation.inplace;

import org.la4j.iterator.VectorIterator;
import org.la4j.vector.Vectors;
import org.la4j.vector.dense.DenseVector;
import org.la4j.vector.functor.VectorFunction;
import org.la4j.vector.operation.VectorOperation;
import org.la4j.vector.sparse.SparseVector;

public class InPlaceVectorByValueMultiplication implements VectorOperation<Void> {

    private double value;
    private VectorFunction mul;

    public InPlaceVectorByValueMultiplication(double value) {
        this.value = value;
        this.mul = Vectors.asMulFunction(value);
    }

    @Override
    public Void apply(SparseVector a) {
        VectorIterator it = a.nonZeroIterator();
        while (it.hasNext()) {
            it.next();
            a.update(it.index(), mul);
        }
        return null;
    }

    @Override
    public Void apply(DenseVector a) {
        for (int i = 0; i < a.length(); i++) {
            a.set(i, a.get(i) * value);
        }
        return null;
    }
}
