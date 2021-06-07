
KEY_PREVIOUS_URL = 'previous-url'
KEY_CURRENT_URL = 'current-url'

class Project:
    def __init__(self, name, dict_info):
        prev = dict_info[KEY_PREVIOUS_URL]
        curr = dict_info[KEY_CURRENT_URL]

        self.name = name
        self.base_url = Project.__get_base_url(prev)
        self.tag_prev = Project.__get_tag(prev)
        self.tag_curr = Project.__get_tag(curr)
    
    def __get_tag(url) -> str:
        tag = '/'.join(url.split('/')[8:])
        return tag[:tag.rfind('.zip')]

    def __get_base_url(url) -> str:
        _index = url.index('github.com') + len('github.com')
        _index = url.index('/', _index) + 1
        _index = url.index('/', _index) + 1
        _index = url.index('/', _index) + 1
        return  url[0:(_index - 1)]

    def get_instance(name, dict_info):
        if not Project.__is_valid(dict_info[KEY_PREVIOUS_URL]) or \
            not Project.__is_valid(dict_info[KEY_CURRENT_URL]):
            return None
        return Project(name, dict_info)

    def __is_valid(url) -> bool:
        if 'github' in url:
            return True
        return False