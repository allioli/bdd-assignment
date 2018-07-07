
import requests
from behave import given, when, then
from hamcrest import equal_to, assert_that, starts_with, less_than_or_equal_to, contains_string


api_key = '419af08f20d7174c0a764c55e22c403a'
guest_session_id = '1013ad7dd606a2f435e0e7b74be62689'
base_movie_db_api_base_url = 'https://api.themoviedb.org/3/movie/'

def compose_moviedb_top_rated_request_url(context):

    if('custom_api_resource' in context.request_params):
        url = base_movie_db_api_base_url + context.request_params['custom_api_resource']
    else:
        url = base_movie_db_api_base_url + "top_rated"

    if('custom_api_key' in context.request_params):
        url += ('?api_key=' + context.request_params['custom_api_key'])
    else:
        url += '?api_key=' + api_key

    if('language' in context.request_params):
        url += ('&language=' + context.request_params['language'])

    if('region' in context.request_params):
        url += ('&region=' + context.request_params['region'])
    
    if('page' in context.request_params):
        url += ('&page=' + context.request_params['page'])

    #print(url)

    return url


def compose_moviedb_rate_movie_request_url(context):

    if('movie_id' not in context.request_params):
        raise Exception('Missing movie_id parameter to build request URL') 

    url = base_movie_db_api_base_url + context.request_params['movie_id'] + '/rating'

    if('custom_api_key' in context.request_params):
        url += ('?api_key=' + context.request_params['custom_api_key'])
    else:
        url += '?api_key=' + api_key
    
    if('custom_guest_session_id' in context.request_params):
        url += ('&guest_session_id=' + context.request_params['custom_guest_session_id'])
    else:
        url += '&guest_session_id=' + guest_session_id
 
    print(url)

    return url


@given(u'I create moviedb API request to rate movie with id "{movie_id}"')
def step_given_I_create_moviedb_api_request(context, movie_id):

    context.request_params = {'movie_id': movie_id}


@given(u'I add parameter "{parameter_name}" with value "{parameter_value}"')
def step_given_I_add_parameter_with_value(context, parameter_name, parameter_value):

    if not hasattr(context, 'request_params'):
        context.request_params = {}

    context.request_params[parameter_name] = parameter_value


@when(u'I perform GET request to movie-db API')
def step_when_I_perform_get_request_to_movie_db_api(context):

    if not hasattr(context, 'request_params'):
        context.request_params = {}

    request_url = compose_moviedb_top_rated_request_url(context)
    context.response = requests.get(request_url)
    context.response_json = context.response.json()

@when(u'I perform POST request to movie-db API')
def step_when_I_perform_post_request_to_movie_db_api(context):

    request_url = compose_moviedb_rate_movie_request_url(context)
    context.response = requests.post(request_url, data = {'value': context.request_params['rating']})
    context.response_json = context.response.json()
    
@then(u'response code is "{response_code:d}"')
def step_then_response_code(context, response_code):

    assert_that(context.response.status_code, equal_to(response_code))


@then(u'response contains "{field}" equal to number "{value:g}"')
def step_then_response_contains_field_number(context, field, value):

    assert_that(context.response_json[field], equal_to(value))

@then(u'response contains "{field}" equal to "{value}"')
def step_then_response_contains_field(context, field, value):

    assert_that(context.response_json[field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" equal to number "{value:g}"')
def step_then_response_contains_movie_with_field_equal_number(context, index, field, value):

    assert_that(context.response_json['results'][index][field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" equal to "{value}"')
def step_then_response_contains_movie_with_field_equal(context, index, field, value):

    assert_that(context.response_json['results'][index][field], equal_to(value))


@then(u'response contains movie in position "{index:d}" with "{field}" starting with "{value}"')
def step_then_response_contains_movie_with_field_starting(context, index, field, value):

    assert_that(context.response_json['results'][index][field], starts_with(value))

@then(u'movies are sorted by "{field}" in descending order')
def step_then_movies_are_sorted_by_field_desc(context, field):

    num_results = len(context.response_json['results'])

    if (num_results > 1):
        for index in range(1, num_results):
            # Check that movie value for field is lower or equal than on the previous one
            movie_value = context.response_json['results'][index][field]
            previous_movie_value =  context.response_json['results'][index-1][field]
            assert_that(movie_value, less_than_or_equal_to(previous_movie_value), 'Movies not correctly sorted')

@then(u'response contains "{result_number:d}" results')
def step_then_response_contains_number_results(context, result_number):
    
    assert_that(len(context.response_json['results']), equal_to(result_number))

@then(u'response contains error message "{error_message}"')
def step_then_response_contains_error_message(context, error_message):
    
    if len(context.response_json['errors']) > 0:
        assert_that(context.response.text, contains_string(error_message))
     
    
  
