// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package ds;

public interface DictionaryOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Dictionary)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, string&gt; dict = 1;</code>
   */
  int getDictCount();
  /**
   * <code>map&lt;string, string&gt; dict = 1;</code>
   */
  boolean containsDict(
      java.lang.String key);
  /**
   * Use {@link #getDictMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getDict();
  /**
   * <code>map&lt;string, string&gt; dict = 1;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getDictMap();
  /**
   * <code>map&lt;string, string&gt; dict = 1;</code>
   */

  java.lang.String getDictOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; dict = 1;</code>
   */

  java.lang.String getDictOrThrow(
      java.lang.String key);
}
