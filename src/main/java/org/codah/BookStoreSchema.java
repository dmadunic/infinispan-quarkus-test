package org.codah;
import org.codah.domain.Author;
import org.codah.domain.Book;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.infinispan.protostream.types.java.CommonContainerTypes;
import org.infinispan.protostream.types.java.CommonTypes;
import org.infinispan.protostream.types.java.math.BigDecimalAdapter;

@AutoProtoSchemaBuilder(
    dependsOn = {
        CommonTypes.class,
        CommonContainerTypes.class
    },
    includeClasses = { Book.class, Author.class, BigDecimalAdapter.class }, 
    schemaPackageName = "book_sample")
interface BookStoreSchema extends GeneratedSchema {
}
