package DAO.DataClasses

data class Note(public var id: Long? = null,
                public var userLogin: String? = null,
                public var title: String? = null,
                public var text: String? = null)